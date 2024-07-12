function find(){
// 创建一个新的 XMLHttpRequest 对象
var xhr = new XMLHttpRequest();

// 设置请求类型为 GET，并指定请求的 URL
xhr.open("GET", "https://console-docs.apipost.cn/preview/65244575310896a1/4dfb8953e65fa578", true);

// 定义一个回调函数来处理服务器的响应
xhr.onload = function() {
  // 检查请求是否成功完成
  if (xhr.status === 200) {
    // 请求成功，将响应数据转换为 JSON 格式
    var data = JSON.parse(xhr.responseText);
    // 在这里可以使用响应数据 data 进行后续处理
    console.log(data);
  } else {
    // 请求失败，处理错误情况
    console.error("请求失败，状态码为：" + xhr.status);
  }
};

// 发送请求
xhr.send();


    }






