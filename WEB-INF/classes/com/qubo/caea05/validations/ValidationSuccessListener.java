package com.qubo.caea05.validations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 入力チェックが成功した場合に、{@link Validator}オブジェクトが呼び出すイベントを表現したインターフェース
 * @author Qubo
 */
public interface ValidationSuccessListener {
	/**
	 * 入力チェックが成功した場合に実行されるイベント
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	void onValidationSuccessed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
