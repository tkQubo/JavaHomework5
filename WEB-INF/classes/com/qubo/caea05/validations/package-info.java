/**
 * ユーザーからの入力チェックに必要な様々なインターフェースおよびクラスを収めたパッケージ。
 * <ul>
 * <li>
 * 	{@link com.qubo.caea05.validations.Validation}インターフェースおよびその実装クラス:<br />
 * ユーザーからの入力を個別にチェックするための機能を提供する。
 * </li>
 * <li>
 * 	{@link com.qubo.caea05.validations.Validator}クラス:<br />
 * サーブレット内で使う。ページ遷移時のユーザー入力チェックとして内部に{@link com.qubo.caea05.validations.Validation}のリストを持ち、それらの結果に応じてページ遷移を行う。
 * </li>
 * <li>
 * 	{@link com.qubo.caea05.validations.ValidationMessage}クラス:<br />
 * ユーザー入力チェックに失敗した時に、HTMLに表示すべきメッセージを格納する。
 * </li>
 * <li>
 * 	{@link com.qubo.caea05.validations.ValidationSuccessListener}インターフェース及び{@link com.qubo.caea05.validations.ValidationFailListener}インターフェース:<br />
 * それぞれユーザー入力の成功時／失敗時に、{@link com.qubo.caea05.validations.Validator}クラスが呼び出すイベントインターフェース。
 * </li>
 * </ul>
 */

package com.qubo.caea05.validations;