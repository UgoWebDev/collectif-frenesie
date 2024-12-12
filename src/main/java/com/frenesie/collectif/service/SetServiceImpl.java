package com.frenesie.collectif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Set;
import com.frenesie.collectif.repository.ArtistRepository;
import com.frenesie.collectif.repository.SetRepository;

@Service
public class SetServiceImpl implements SetService {

	private SetRepository setRepository;
	private ArtistRepository artistRepository;
	
	public SetServiceImpl(SetRepository repository, ArtistRepository artistRepository) {
		this.setRepository = setRepository;
		this.artistRepository = artistRepository;
	}

	@Override
	public void add(Set entity) {
		setRepository.add(entity);
	}

	@Override
	public List<Set> getAll() {
		return setRepository.getAll();
	}

	@Override
	public Optional<Set> getById(int id) {
		return setRepository.getById(id);
	}

	@Override
	public void update(Set entity) {
		setRepository.update(entity);
	}

	@Override
	public void save(Set set) {
		if(set.getId()!=null) {
			setRepository.update(set);
		} else {
			setRepository.add(set);
		}
	}

	@Override
	public void delete(int id) {
		setRepository.delete(id);
	}
	
	
}
