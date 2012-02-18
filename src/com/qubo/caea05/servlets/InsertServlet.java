package com.qubo.caea05.servlets;

import com.qubo.caea05.validations.Validation;
import com.qubo.caea05.validations.Validations;

/**
 * 新規登録画面用のサーブレット
 * @author Qubo
 */
public class InsertServlet extends ValidationServlet {
	/** シリアルバージョンID */
	private static final long serialVersionUID = -6402372893163918325L;
	/** 入力ページ */
	public static final String INPUT_PAGE = "WEB-INF/insert.jsp";
	/** 出力ページ */
	public static final String OUTPUT_PAGE = "WEB-INF/insertConfirm.jsp";

	/** コンストラクタ */
	public InsertServlet() {
		super(INPUT_PAGE, OUTPUT_PAGE, true);
	}

	/*
	 * (非 Javadoc)
	 * @see com.qubo.caea05.servlets.ValidationServlet#getValidations()
	 */
	@Override
	protected Validation[] getValidations() {
		return new Validation[] { Validations.passwordRequired, Validations.userNameRequired };
	}
}
