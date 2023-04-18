function prepareEmployeeIds() {
  var checkedEmployeeIds = [];
  $("input[name='employeeCheckbox']:checked").each(function () {
      checkedEmployeeIds.push($(this).val());
  });
  $("#employeeIds").val(checkedEmployeeIds.join(','));
}