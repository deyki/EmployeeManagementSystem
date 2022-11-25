package EMS.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @SequenceGenerator(name = "contract_sequence", sequenceName = "contract_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_sequence")
    private Long contractID;

    @Column(name = "job_position", nullable = false)
    private String jobPosition;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "date_of_termination")
    private Date dateOfTermination;

    @Column(name = "net_salary", nullable = false)
    private Integer netSalary;

    @Column(name = "terminated")
    private Boolean terminated;

    @OneToOne(mappedBy = "contract")
    @JsonIgnore
    private User user;
}
