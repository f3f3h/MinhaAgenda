package MinhaAgenda;

import java.time.LocalDate;

//Superclass
public class Tarefas {
    private String descricao; //encapsulamento
    private String status;
    private LocalDate deadline;


    // Construtor que cria a tarefa nesse formato definido
    public Tarefas (String descricao, String status, LocalDate deadline) {
        this.descricao = descricao;
        this.status = status;
        this.deadline = deadline;

    }

    //metodo get para exibir a resumo da tarefa
    public String getResumoTarefa() {
        // Armazena a data de hoje
        LocalDate hoje = LocalDate.now();

        // Verifica se a tarefa está em atraso comparando a deadline com a data atual
        boolean emAtraso = deadline.isBefore(hoje);

        // Monta o resumo da tarefa
        String resumo = this.getClass().getSimpleName() + "\n" +
                "To Do: " + descricao + "\n"
                + "Status: " + status + "\n"
                + "Deadline: " + deadline + "\n";

        // Se estiver em atraso, adiciona o aviso no final
        if (emAtraso) {
            resumo += "⚠️ ATENÇÃO: Essa tarefa está EM ATRASO! \n";
        }

        return resumo;
    }


    // Getter e Setter para descricao
    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


    // Getter e Setter para status
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }


    // Getter e Setter para deadline
    public LocalDate getDeadline(){
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }


}