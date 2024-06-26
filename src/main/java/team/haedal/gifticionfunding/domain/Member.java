package team.haedal.gifticionfunding.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import team.haedal.gifticionfunding.domain.enums.Role;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    private Long point;
    private LocalDate birthdate;
    @Nullable
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private Role role;

}
