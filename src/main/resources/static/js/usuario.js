
//////////////////////////////////

function criarTabela(){
	
	
	
}

//////////////////////////////////

function cadastrarUsuario(){
	
	let usuario = {
		"nome": document.getElementById('nome').value,
		"telefone": document.getElementById('tel').value,
		"email": document.getElementById('email').value,
		"senha": document.getElementById('senha').value
	};
	
		
	fetch("/usuario", {
		
		method: "POST",
		headers: {
			"content-type": "application/JSON"
		},
		
		body: JSON.stringify(usuario)
		
	}).then(function(response){
		document.location = 'posts.html';
		
	}).catch(function(error){
		console.log(error);
	});
	
}