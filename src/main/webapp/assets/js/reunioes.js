/**
 * 
 */

const Processos = {
	"processos": []
}

Processos.fetchData = async function(id) {
	const processo = await fetch("controller?command=BuscarProcesso", {method: "POST", body: id})
							.then(res => res.json())
								.then(json => Processos.add(json));
	
}

Processos.add = function(p) {
	if(this.exists(p.numero)){
		alert("Este processo ja foi adicionado!");
		return;
	}
	this.processos.push(p);
	var row = `<tr>
					<td>${p.numero}</td>
					<td>${p.requisitante}</td>
					<td>${p.assunto}</td>
					<td>${p.relator}</td>
					<td><button id="${p.id}" class="btn btn-danger">Excluir</button></td>
				</tr>`;
	var customSelect = document.querySelector(".custom-select");
	var input = `<input type='checkbox' name='processo' value='${customSelect.value}' checked style='display:none;'>`;
	const table = document.getElementById("processos-table");
	const form = document.getElementById("criarReuniao");
	form.insertAdjacentHTML("beforeend", input);
	table.insertAdjacentHTML("beforeend", row);
	const botaoExcluir = document.getElementById(`${p.numero}`).addEventListener("click", (e) => {
		let row = e.target.parentNode.parentNode;
		let parent = row.parentNode;
		parent.removeChild(row);
		this.remove(p.numero);
	});
}

Processos.remove = function(num) {
	this.processos = this.processos.filter(p => p.numero != num);
	console.log(this.processos);
}

Processos.exists = function(num) {
	return this.processos.filter(p => p.numero == num).length ? true: false;
}


document.getElementById("processo-button").addEventListener("click", () => {
	const processoId = document.getElementById("processos-select").value;
	if(processoId == "Escolher...") return;
	Processos.fetchData(processoId);
});

const criarReuniao = () => {
	const nome = document.getElementById("")
}