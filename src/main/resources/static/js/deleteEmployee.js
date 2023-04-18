function deleteEmployees() {
  var employeeIds = [];
  $("input[name='employeeIds']:checked").each(function () {
    employeeIds.push($(this).val());
  });
  if (employeeIds.length > 0) {
    if (confirm("選択された社員を削除しますか？")) {
      $("#employeeIds").val(employeeIds.join(','));
      $("#deleteForm").submit();
    }
  } else {
    alert("削除する社員を選択してください。");
  }
}