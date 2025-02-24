package vn.a18156.test.shared.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "Tên không quá 50 ký tự")
    @Size(min = 3,message = "Tên quá ngắn, ít nhất 3 ký tự")
    private String name;

    private Boolean gender;

    private String address;
    @Size(min=10,max=10, message = "Độ dài số điện thoại phải là 10")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại chỉ được chứa các chữ số")
    @NotBlank(message = "Chưa có số điện thoại")
    private String phone;

    @Column(name = "create_at", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
}
