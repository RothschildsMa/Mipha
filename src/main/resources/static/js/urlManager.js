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

function deleteData() {
        var checkboxes = document.getElementsByName("selectedEmployees");
        var checked = false;
        for(var i = 0; i < checkboxes.length; i++){
			if (checkboxes[i].checked) {
				checked = true;
          		break;
        	}
		}
		
		if (checked) {
			if(confirm("選択した社員情報一覧を削除してもよろしいですか。")){
				submitForm('selectForm','delete','post');
				 //<!-- location.reload(); -->
			}else{
				event.preventDefault(); //submitキャンセル
			}
        } else {
			alert("削除する社員を選択してください。");
			event.preventDefault(); //submitキャンセル
        }
      }

 //MD5 暗号化パスワードの使用
 function md5(password) {
  const md5 = crypto.createHash('md5');
  return md5.update(password).digest('hex');
}