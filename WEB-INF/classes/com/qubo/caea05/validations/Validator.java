package com.qubo.caea05.validations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * サーブレット上で、ユーザーの入力チェックを行いその結果に応じて
 * ページ遷移を決定するためのクラス。
 * 入力チェックが成功した場合は{@link #getOutputPage()}へ遷移し、
 * 失敗した場合は{@link #getInputPage()}へ遷移してユーザーに再度のデータ入力を求めさせる。
 * @author Qubo
 */
public class Validator {
	public static final String DEFAULT_PAGE_ERROR = "WEB-INF/error.jsp";

	private final String inputPage;
	private final String outputPage;
	private final String errorPage;
	private final List<Validation> validations;
	private ValidationSuccessListener validationSuccessListener;
	private ValidationFailListener validationFailListener;

	/**
	 * 入力ページを取得する
	 * @return 入力ページ
	 */
	public String getInputPage() { return inputPage; }
	/**
	 * 出力ページを取得する
	 * @return 出力ページ
	 */
	public String getOutputPage() { return outputPage; }
	/**
	 * エラーページを取得する
	 * @return エラーページ
	 */
	public String getErrorPage() { return errorPage; }
	/**
	 * {@link ValidationSuccessListener}インスタンスを取得する
	 * @return {@link ValidationSuccessListener}インスタンス
	 */
	public ValidationSuccessListener getValidationSuccessListener() { return validationSuccessListener; }
	/**
	 * {@link ValidationFailListener}インスタンスを取得する
	 * @return {@link ValidationFailListener}インスタンス
	 */
	public ValidationFailListener getValidationFailListener() {return validationFailListener; }
	/**
	 * {@link ValidationSuccessListener}インスタンスを設定する
	 * @param listener {@link ValidationSuccessListener}インスタンス
	 */
	public void setValidationSuccessListener(ValidationSuccessListener listener) { this.validationSuccessListener = listener; }
	/**
	 * {@link ValidationFailListener}インスタンスを設定する
	 * @param listener {@link ValidationFailListener}インスタンス
	 */
	public void setValidationFailListener(ValidationFailListener listener) { this.validationFailListener = listener; }

	/**
	 * コンストラクタ
	 * @param inputPage 入力ページ
	 * @param outputPage 出力ページ
	 * @param errorPage エラーページ
	 */
	public Validator(String inputPage, String outputPage, String errorPage) {
		this.inputPage = outputPage;
		this.outputPage = inputPage;
		this.errorPage = errorPage;
		this.validations = new ArrayList<Validation>();
	}
	/**
	 * コンストラクタ
	 * @param inputPage 入力ページ
	 * @param outputPage 出力ページ
	 * @param errorPage エラーページ
	 * @param validations {@link Validation}配列
	 */
	public Validator(String inputPage, String outputPage, String errorPage, Validation... validations) {
		this(inputPage, outputPage, errorPage);
		for (Validation validation : validations) {
			this.validations.add(validation);
		}
	}
	/**
	 * コンストラクタ
	 * @param inputPage 入力ページ
	 * @param outputPage 出力ページ
	 */
	public Validator(String inputPage, String outputPage) {
		this(inputPage, outputPage, DEFAULT_PAGE_ERROR);
	}
	/**
	 * コンストラクタ
	 * @param inputPage 入力ページ
	 * @param outputPage 出力ページ
	 * @param validations {@link Validation}配列
	 */
	public Validator(String inputPage, String outputPage, Validation... validations) {
		this(inputPage, outputPage, DEFAULT_PAGE_ERROR);
		for (Validation validation : validations) {
			this.validations.add(validation);
		}
	}

	/**
	 * 入力チェックのための{@link Validation}インスタンスを追加する
	 * @param validation {@link Validation}インスタンス
	 * @return
	 */
	public Validator add(Validation validation) {
		this.validations.add(validation);
		return this;
	}
	/**
	 * ユーザーからのデータ入力チェックを行い、その成否に応じてページ遷移を行う。
	 * @param request {@link HttpServletRequest}オブジェクト
	 * @param response {@link HttpServletResponse}オブジェクト
	 * @throws ServletException
	 * @throws IOException
	 */
	public void validateAndForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int level = 0;
		List<String> messages = new ArrayList<String>();
		for (Validation validation : validations) {
			boolean shouldTest = messages.size() == 0 || !validation.skipWhenAlreadyFailed();
			if (shouldTest && !validation.test(request)) {
				if (validation.getLevel() > level) {
					level = validation.getLevel();
					messages.clear();
				}
				messages.add(validation.getMessage());
			}
		}

		if (messages.size() > 0) {
			if (validationFailListener != null) {
				validationFailListener.onValidationFailed(request, response);
			}
			ValidationMessage validationMessage = new ValidationMessage(level, messages);
			request.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher((level < Validation.LEVEL_ERROR) ? outputPage : errorPage).forward(request, response);
		} else {
			if (validationSuccessListener != null) {
				validationSuccessListener.onValidationSuccessed(request, response);
			}
			request.getRequestDispatcher(inputPage).forward(request, response);
		}
	}
}
