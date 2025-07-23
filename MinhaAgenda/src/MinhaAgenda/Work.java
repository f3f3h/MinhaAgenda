package MinhaAgenda;

import java.time.LocalDate;

class Work extends Tarefas{
    public Work(String descricao, String status, LocalDate deadline) {
        super(descricao, status, deadline);
    }
}