
function isEmailValido(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

function isCPFValido(cpf) {
    cpf = cpf.replace(/[^\d]+/g, ''); 
    if (cpf == '') return false;
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999") 
            return false;
            
    let add = 0;
    for (let i = 0; i < 9; i ++) add += parseInt(cpf.charAt(i)) * (10 - i);
    let rev = 11 - (add % 11);
    if (rev == 10 || rev == 11) rev = 0;
    if (rev != parseInt(cpf.charAt(9))) return false;
    
    add = 0;
    for (let i = 0; i < 10; i ++) add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11) rev = 0;
    if (rev != parseInt(cpf.charAt(10))) return false;
    
    return true;
}

function isDataNascimentoValida(dataString) {
    if(!dataString) return false;
    const dataNasc = new Date(dataString);
    const hoje = new Date();
    
    if (dataNasc > hoje) return false;
    
    let idade = hoje.getFullYear() - dataNasc.getFullYear();
    const m = hoje.getMonth() - dataNasc.getMonth();
    if (m < 0 || (m === 0 && hoje.getDate() < dataNasc.getDate())) {
        idade--;
    }
    return idade >= 16;
}

function mascaraCPF(i) {
    var v = i.value;
    
    v = v.replace(/\D/g, "");
    i.setAttribute("maxlength", "14");
    v = v.replace(/(\d{3})(\d)/, "$1.$2");
    v = v.replace(/(\d{3})(\d)/, "$1.$2");
    v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    
    i.value = v;
   
}

function mascaraCelular(i) {
    var v = i.value;
    i.setAttribute("maxlength", "15");
    v = v.replace(/\D/g, "");             
    v = v.replace(/^(\d{2})(\d)/g, "($1) $2"); 
    v = v.replace(/(\d)(\d{4})$/, "$1-$2");    
    i.value = v;
}

function mascaraMoeda(i) {
    var v = i.value.replace(/\D/g,'');
    v = (v/100).toFixed(2) + '';
    v = v.replace(".", ",");
    v = v.replace(/(\d)(\d{3})(\d{3}),/g, "$1.$2.$3,");
    v = v.replace(/(\d)(\d{3}),/g, "$1.$2,");
    i.value = "R$ " + v;
}



function validarFormularioLogin(event) {
    const form = document.forms["formLogin"];
    const email = form["login"].value;
    const senha = form["senha"].value;
    let msgErro = [];

    if (!isEmailValido(email)) {
        msgErro.push("O e-mail informado é inválido.");
    }

    if (senha.length < 6) {
        msgErro.push("A senha deve ter pelo menos 6 caracteres.");
    }

    if (msgErro.length > 0) {
        alert("Erro no Login:\n- " + msgErro.join("\n- "));
        event.preventDefault();
        return false;
    }
    return true; 
}
function validarFormularioCadastro(event) {
    const form = document.forms["formCadastro"];
    const nome = form["nome"].value.trim();
    const cpf = form["cpf"].value;
    const rg = form["rg"].value;
    const data = form["dataNasc"].value;
    const celular = form["celular"].value;
    const email = form["email"].value;
    const senha = form["senha"].value;
    
    let msgErro = [];

    if (nome.split(" ").length < 2) {
        msgErro.push("Por favor, informe seu nome completo (Nome e Sobrenome).");
    }

    if (!isCPFValido(cpf)) {
        msgErro.push("CPF inválido. Verifique os dígitos.");
    }

    if (rg.length < 5) {
        msgErro.push("RG parece inválido.");
    }

    if (!isDataNascimentoValida(data)) {
        msgErro.push("Data de nascimento inválida. Você deve ter no mínimo 16 anos.");
    }

    if (celular.length < 14) {
        msgErro.push("Número de celular incompleto.");
    }

    if (!isEmailValido(email)) {
        msgErro.push("Formato de e-mail inválido.");
    }

    if (senha.length < 6) {
        msgErro.push("A senha deve ter no mínimo 6 caracteres.");
    }

    if (msgErro.length > 0) {
        alert("Corrija os seguintes erros no cadastro:\n- " + msgErro.join("\n- "));
        event.preventDefault();
        return false;
    }
    return true;
}

function validarFormularioServico(event) {
    const form = document.forms[0]; 
    
    const titulo = form.querySelector("input[name='titulo']").value.trim();
    const preco = form.querySelector("input[name='preco']").value.trim();
    const desc = form.querySelector("textarea[name='descricao']").value.trim();
    const exp = form.querySelector("input[name='experiencia']").value;

    let msgErro = [];

    if (titulo.length < 3) {
        msgErro.push("O Título da Profissão deve ser descritivo.");
    }

    let precoNumerico = preco.replace(/[^\d,]/g, '').replace(',', '.');
    if (preco === "" || isNaN(parseFloat(precoNumerico)) || parseFloat(precoNumerico) <= 0) {
        msgErro.push("Informe um Preço Base válido.");
    }

    if (desc.length < 10) {
        msgErro.push("A descrição deve ter pelo menos 10 caracteres para atrair clientes.");
    }

    if (exp === "" || parseInt(exp) < 0) {
        msgErro.push("O tempo de experiência não pode ser negativo.");
    }

    if (msgErro.length > 0) {
        alert("Atenção nos detalhes do serviço:\n- " + msgErro.join("\n- "));
        event.preventDefault();
        return false;
    }
    return true;
}
