package MinhaAgenda;

import java.time.LocalDate;

class Personal extends Tarefas {
    public Personal(String descricao, String status, LocalDate deadline) {
        super(descricao, status, deadline);
    }
}