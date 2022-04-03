package aula03.atividades;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjetoMercadoDigital {
    public static void main(String... args) {

/*** PROJETO MERCADO DIGITAL COM CARRINHO DE COMPRAS

 Faremos uma Loja virtual com Carrinho de Compras!!!

 Em resumo vamos buscar produtos por código, adicionar quantidades dos itens selecionados, somar os
 preços de diversos itens que queremos comprar e adicionar desconto dependendo da forma do
 pagamento.

 Requisitos:
 [x] - A nossa loja deve conter estoque;
 [] - Caso produto esteja indisponível deve retornar a mensagem para o cliente “Produto Indisponível”;
 [] - Deverá ter 3 formas de pagamento;
 [] - À vista (dinheiro, pix ou cartão MasterdCard) tem 20% de desconto;
 [] - À vista no crédito tem 10% de desconto;
 [] - Parcelado em até 3x não tem desconto;
 [] - Deve retornar a nota fiscal com o valor pago de tributos de 9% sobre a compra (Esse valor não deve ser
        somado ao total do carrinho, somente ser exibido na nota fiscal);

 [x] - Caprichem no README lá vocês podem contar como fizeram e como foi o trabalho em equipe.
 [x] - O Projeto pode ser feito somente em uma classe ou da forma que acharem melhor;
 [x] - O PROGRAMA TEM QUE RODAR NO CONSOLE.

 HELPERS
 Usando Objetos como valor no Map<k,v>
 https://pt.stackoverflow.com/questions/257120/%C3%89-possivel-hashmap-com-varios-valores

 Conhecendo a interface Map<k,v>
 https://www.devmedia.com.br/conhecendo-a-interface-map-do-java/37463
 https://www.alura.com.br/artigos/iterando-por-um-hashmap-em-java?gclid=Cj0KCQjw6J-SBhCrARIsAH0yMZjWlIxQjs5YxdCBoQFQlz1BzMRVEnwH0ab2HRf0mVmGnnBq_DS75ckaAvOfEALw_wcB

 ***/

        String[] produtos = {"Leite", "Cereal", "Arroz", "Atum", "Feijão", "Azeite", "Óleo", "Sabão", "Sal", "Açucar"};
        Integer [] estoque = {10,10,10,10,10,10,10,10,10,10};
        Float [] precos = {4.57f, 3.02f,9.43f,3.55f,6.55f,4.55f,7.33f,1.99f,3.82f,4.29f };

        ArrayList<Integer> carrinhoCodigoProduto = new ArrayList<>();
        ArrayList<Integer> carrinhoQuantidade = new ArrayList<>();

        boolean checker = false;
        do {
            String choice;
            int code = 0, quantidade = 0;
            Scanner sc = new Scanner(System.in);

            System.out.printf("%n\t\t\t WIPRO STORE%n");
            System.out.println("--- --- --- --- --- --- --- --- --- --- --- ---");
            System.out.printf("CODIGO\t\tPRODUTO \t QUANTIDADE \t PREÇO %n");
            for (int x = 0; x < produtos.length; x++) {
                System.out.printf("  %s \t\t %s\t\t\t %s \t\t %s %n", x + 1, produtos[x], estoque[x], precos[x]);
            }
            System.out.printf("--- --- --- --- --- --- --- --- --- --- --- ---\n");
            System.out.println("Olá! Digite o código do produto desejado: ");
            code = sc.nextInt();


                if(code > produtos.length){
                    System.out.println("O produto informado não esta na lista no momento, selecione outro produto");
                    continue;
                }
                else
                {
                    carrinhoCodigoProduto.add(code);
                }

                System.out.println("Olá! Digite a quantidade que deseja colocar no carrinho: ");
            quantidade = sc.nextInt();


            if(quantidade > estoque[code]){
                System.out.println("A quantidade informada é maior do que o numero disponivel em estoque");
                continue;
            }
            else
            {
                carrinhoQuantidade.add(quantidade);
            }

            System.out.println("Deseja continuar as compras?");
            System.out.printf("--- --- --- --- --- --- --- --- --- ---%n"+
                    "[S] - Incluir mais itens no seu carrido.%n" +
                    "[N] - Ir para carrinho de compras e formas de pagamento.%n");
            System.out.println("--- --- --- --- --- --- --- --- --- ---");
            choice = sc.next();
            if(choice.matches("sim") || choice.matches("s")){
                System.out.println("saindo da lista");
                continue;
            }
            else if(choice.matches("nao") || choice.matches("n"))
            {
            carrinhoDeCompras(carrinhoCodigoProduto,carrinhoQuantidade,produtos,precos);

            checker = true;
            }

        }while(!checker);
    }

    // === === === === === === === ===
    // CARRINHO DE COMPRAS

    public static void carrinhoDeCompras(
            ArrayList<Integer> carrinhoCodigoProduto, ArrayList<Integer> carrinhoQuantidade,
            String[] produtos, Float[] precos
    )
    {
        float valorTotalDeCompra = 0;
        float valorImposto=0;
        System.out.printf("\t\t ITENS NO CARRINHO%n"+
                "--- --- --- --- --- --- --- --- --- ---%n"+
                "PRODUTO \tPREÇO \tQUANTIDADE \tTOTAL%n");
        for(int i = 0; i < carrinhoCodigoProduto.size(); i++)
        {
            System.out.printf("%s\t\t%s\t\t%s\t\t%s%n",
                    produtos[carrinhoCodigoProduto.get(i)],
                    precos[carrinhoCodigoProduto.get(i)],
                    carrinhoQuantidade.get(i),
                    precos[carrinhoCodigoProduto.get(i)] * carrinhoQuantidade.get(i)
            );
            valorTotalDeCompra += (precos[carrinhoCodigoProduto.get(i)] * carrinhoQuantidade.get(i));
            valorImposto = (9.0f / 100.0f) *valorTotalDeCompra;

        }
        System.out.println("\t\t TOTAL ");
        System.out.printf("TOTAL - O valor total da compra R$: %s%n", valorTotalDeCompra + valorImposto, valorImposto);
        System.out.printf("IMPOSTOS - Valor total de imposto correspondente a 9 porcento sobre o valor total R$: %s%n", valorImposto);
        System.out.printf("TOTAL + IMPOSTOS - O valor total da compra com Imposto de 9 porcento é de R$: %s%n", valorTotalDeCompra + valorImposto);
        System.out.printf("%n%n--- --- --- --- --- --- --- --- --- ---%n"+
                        "[1] - Escolher forma de pagamento. %n" +
                        "[2] - Cancelar compra e esvaziar carrinho. %n" +
                        "--- --- --- --- --- --- --- --- --- ---");

    }

    // === === === === === === === ===
    // FORMAS DE PAGAMENTO
    public void formasDePagamento(){
        System.out.printf("\t\t FORMAS DE PAGAMENTO");
        System.out.printf(
                "--- --- --- --- --- --- --- --- --- ---%n"+
                        "[1] - À vista em dinheoro ou cartão MASTERCARD, recebe 20% de desconto%n" +
                        "[2] - À vista no cartão de crédito, recebe 15% de desconto%n" +
                        "[3] - Em duas vezes, preço normal de etiqueta sem juros%n" +
                        "[4] - Em três vezes, preço normal de etiqueta mais juros de 10% %n%n" +
                        "Qual seria a forma de pagamento ?");
    }
}