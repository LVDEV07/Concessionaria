# API Concessionária Marcelo Gomes

Projeto da disciplina, feito a partir do case de uma concessionária de
carros. 




## Ambiguidades e o que eu decidi

- O cliente informou que utiliza os dois canais para mandar promoções, mas não especificou quais. Conclui que era o telefone e o email. Dessa forma tornei eles únicos (Evitar que diversos clientes tenham o mesmo número) e obrigatórios, par que seja possível o vendedor sempre entrar em contato.
- O Cliente mencionou que carros novos podem não ter placa então decidi deixar a propriedade como `nullabe false`.
- 