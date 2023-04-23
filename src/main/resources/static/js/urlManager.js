/**
 * URLパス管理マネージャー
 * 
 */

var urlPaths = {
	home:   '/employee/employInformationDisplay',
	add:    '/emp/add',
	delete: '/deleteEmployees',
	logout: '/logout'
};
 
 
// URLパスを取得する関数
function getUrlPath(key) {
  return urlPaths[key] || '';
}

function submitForm(formId,keyName,method = undefined) {
	var form = document.getElementById(formId); // フォーム要素を取得
    form.action = getUrlPath(keyName); // フォームのaction属性にURLを設定
    if(method !== undefined) form.method = method;    // フォームのmethod属性にHTTPメソッドを設定
    form.submit(); // フォームを送信
}