package com.epam.university.spring.core.dao.JdbcStorage;

import com.epam.university.spring.core.dao.RetreiveFieldsValues;
import com.epam.university.spring.core.dao.Storable;

/**
 * User: Nikita_Dobriukha
 * Date: 2015-11-10
 * Time: 17:39
 */
public class DummyObject implements Storable<Long>, RetreiveFieldsValues {
    private Long id;
    private String name;

    public DummyObject() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object[] getFieldsValues() {
        return new Object[] {getName()};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DummyObject dummyObject = (DummyObject) obj;
        if (!id.equals(dummyObject.id)) return false;
        return name.equals(dummyObject.name);
    }
}
