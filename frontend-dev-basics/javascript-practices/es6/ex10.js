// Parenthesis-less function & template literal

div = function(s, ...v){
  console.log(s, v);
  return function(){
    return <div style={{}}>{}</div>;
  }
}

// 이렇게도 함수 호출이 가능함
div`
  color: #fff;
  font-size:20px;
  font-weight:bold;
`

