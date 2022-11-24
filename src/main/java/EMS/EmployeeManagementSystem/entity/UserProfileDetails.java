package EMS.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_profile_details")
public class UserProfileDetails {

    @Id
    @SequenceGenerator(name = "user_profile_details_sequence", sequenceName = "user_profile_details_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_details_sequence")
    private Long userProfileDetailsID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private Integer phoneNumber;

    @OneToOne(mappedBy = "userProfileDetails")
    @JsonIgnore
    private User user;
}
