# Back Junit App

### Jpa LocalDateTime 자동으로 생성하는 법
- @EnableJpaAuditing (Main class)
- @EntityListeners(AuditingEntityListener.class) (Entity class)
```java
    @CreatedDate // insert
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // insert, update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

```




https://github.com/codingspecialist/junit-bank-class/blob/main/class-note/api/API.md