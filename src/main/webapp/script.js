let testOutVal = ''

testInput = () => {
    let userIn = document.querySelector('#input').value
    console.log(userIn);
    testOutVal+= userIn + "\n";
    document.getElementById('testOut').innerHTML = testOutVal;
}