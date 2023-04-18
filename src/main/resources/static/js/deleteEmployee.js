 function deleteEmployees() {
  var employeeIds = [];
  $("input[name='employeeCheckbox']:checked").each(function () {
    employeeIds.push($(this).val());
  });
  if (employeeIds.length > 0) {
    $("#employeeIds").val(employeeIds.join(','));
    $("#deleteForm").submit();
  } else {
    alert("削除する社員を選択してください。");
  }
}