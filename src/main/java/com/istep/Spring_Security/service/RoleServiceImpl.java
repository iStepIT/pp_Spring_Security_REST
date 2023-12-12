package com.istep.Spring_Security.service;

import com.istep.Spring_Security.models.Role;
import com.istep.Spring_Security.repository.RoleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getListRoles() {
        return roleRepository.findAll(Sort.by("name"));
    }
}