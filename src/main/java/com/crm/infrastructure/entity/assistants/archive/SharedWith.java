package com.crm.infrastructure.entity.assistants.archive;

import com.crm.infrastructure.entity.Identifiable;
import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.entity.enums.SharedType;

import javax.persistence.*;

@Entity
@Table(name="shared_files_with")
public class SharedWith extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="file_info_id")
    private FileInfo fileInfo;

    @Enumerated(EnumType.STRING)
    private SharedType type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public SharedType getType() {
        return type;
    }

    public void setType(SharedType type) {
        this.type = type;
    }
}
