
///////////////////////////////
function criarTabela(){
	
	
	
	fetch("/tweet").then(function(response){
		
		
		
		if(response.status >= 200 && response.status <= 300){
			
			response.json().then(function(data){
				
				let tabPost = document.getElementById('publi');
				
				tabPost.innerHTML = '';
				//tabPost.innerHTML = '<div class="alert alert-light" id="publi"><span class="glyphicon glyphicon-trash" style="font-size:20px; color: red"></span></div>';
				
				for(let i = 0 ; i < data.content.length ; i++){
					let a = data.content[i];	
					tabPost.innerHTML += `<div class="alert alert-light" id="publi">${a.descricao}<span class="glyphicon glyphicon-trash" onclick="deletarPost(${a.id})" style="font-size:20px; color: red;"></span></div><br>`;	
				}	
			})
		}
		
	}).catch(function(error){
		console.log(error);
	});
	
}

///////////////////////////////
function cadastrarTweet(){
	
	console.log("antes");
	
	let post = {	
		"descricao": document.getElementById('comment').value
	};
	
	console.log("depois");
	
	console.log(post);
	fetch("/tweet", {
		
		method: "POST",
		headers: {
			"content-type": "application/JSON"
		},
		
		body: JSON.stringify(post)
		
	}).then(function(response){
		
		criarTabela();
		
	}).catch(function(error){
		console.log(error);
	});
	
}

///////////////////////////////

function deletarPost(id){
	
	fetch("/tweet/"+id, {
		
		method: "DELETE",
	}).then(function(response){
		
		criarTabela();
		
	}).catch(function(error){
		console.log(error);
	});
	
}

///////////////////////////////


criarTabela();