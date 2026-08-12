## Ambiguidades e o que eu decidi

- O case não diz quais canais de contato usar pra promoção. Decidi usar
  telefone e e-mail, tornando os dois obrigatórios e únicos.

- Carro novo pode chegar sem placa (ela sai depois do emplacamento). Por
  isso o campo `placa` aceita nulo.

- O case trata ano de fabricação e ano do modelo como coisas diferentes.
  Por isso criei os dois campos separados.

- O case não define se a cor é uma lista fixa. Deixei texto livre, já que
  cada marca nomeia as cores de um jeito diferente.

- O chassi é obrigatório e único, mesmo sem placa, porque é ele que
  identifica o carro assim que ele chega na concessionária.

- O `CarroResumoDto` mostra todos os campos, pensando em uso interno. Já
  o `ClienteResumoDto` não mostra o CPF, por ser um dado sensível.

- Limitei o ano de fabricação até 2026 e o ano do modelo até 2027, pra
  evitar cadastro com ano errado, tipo 2202.

- Validei o CPF com um código simples que verifica se cada caractere é
  um número, sem usar nenhuma ferramenta pronta.