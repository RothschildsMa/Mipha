function deleteEmployees() {
  var employeeIds = [];
  $("input[name='employeeIds']:checked").each(function () {
    employeeIds.push($(this).closest('tr').find('td:eq(1)').text());
  });
  if (employeeIds.length > 0) {
    if (confirm("選択された社員を削除しますか？")) {
      var form = $("#deleteForm");
      for (var i = 0; i < employeeIds.length; i++) {
        form.append("<input type='hidden' name='employeeIds' value='" + employeeIds[i] + "'>");
      }
      form.submit();
    }
  } else {
    alert("削除する社員を選択してください。");
  }
}