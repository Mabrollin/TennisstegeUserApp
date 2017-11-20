package org.tennisstege.api.rest.mapper;

public interface Mapper<DTO, Entity> {
	public DTO  mapToDTO(Entity entity);
	public Entity mapToEntity(DTO dto);
}
