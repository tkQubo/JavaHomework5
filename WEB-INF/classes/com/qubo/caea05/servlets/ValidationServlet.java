package com.qubo.caea05.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.ValidationFailListener;
import com.qubo.caea05.validations.ValidationSuccessListener;
import com.qubo.caea05.validations.Validator;

/**
 * ユーザー入力のチェック機能を実装するサーブレット抽象クラス。
 * このクラスを継承したクラスは、POSTリクエスト時に以下の挙動を行う（実際の処理は内部の{@link Validator}インスタンスが行う ）。
 * <ol>
 * <li>{@link #getValidations()}のそれぞれの{@link Validation#test(HttpServletRequest)}メソッドを呼び出し、ユーザー入力のチェックを行う。</li>
 * <li>そこで一つでも{@link Validation#test(HttpServletRequest)}が{@code false}を返した場合は、入力チェック失敗と見なす。</li>
 * <li>入力チェックが成功した場合は、コンストラクタで渡した{@code outputPage}に遷移する。<br />
 * その際、このクラスが{@link ValidationSuccessListener}を実装している場合は
 * {@link ValidationSuccessListener#onValidationSuccessed(HttpServletRequest, HttpServletResponse)}を実行する。</li>
 * <li>入力チェックが失敗した場合は、コンストラクタで渡した{@code inputPage}に遷移し、入力失敗メッセージを表示させる。<br />
 * その際、このクラスが{@link ValidationFailListener}を実装している場合は
 * {@link ValidationFailListener#onValidationFailed(HttpServletRequest, HttpServletResponse)}を実行する。</li>
 * </ol>
 * @author Qubo
 */
public abstract class ValidationServlet extends AuthenticationServlet {
	/** シリアルバージョンID */
	private static final long serialVersionUID = 3033708484646734207L;
	/** エラーページ */
	public static final String DEFAULT_PAGE_ERROR = "WEB-INF/error.jsp";
	private final String inputPage;
	private final String outputPage;

	/**
	 * 入力チェックに必要な{@link Validation}オブジェクトの配列を取得する
	 * @return {@link Validation}オブジェクトの配列
	 */
	protected Validation[] getValidations() { return new Validation[0]; }

	/**
	 * コンストラクタ
	 * @param inputPage 入力ページ（初期ページ、および入力チェックの失敗時に遷移するページ）
	 * @param outputPage 出力ページ（入力チェックの成功時に遷移するページ）
	 * @param hasStartPage 初期ページを持つかどうか（{@code false}の場合、このサーブレットへのGETリクエストはエラーとなる）
	 */
	public ValidationServlet(String inputPage, String outputPage, boolean hasStartPage) {
		super(hasStartPage ? inputPage : null);
		this.inputPage = inputPage;
		this.outputPage = outputPage;
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.AuthenticationServlet#doPostAuthenticated(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPostAuthenticated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Validator validator = new Validator(inputPage, outputPage, DEFAULT_PAGE_ERROR, getValidations());
		if (this instanceof ValidationSuccessListener) {
			validator.setValidationSuccessListener((ValidationSuccessListener) this);
		}
		if (this instanceof ValidationFailListener) {
			validator.setValidationFailListener((ValidationFailListener) this);
		}
		validator.validateAndForward(request, response);
	}
}
