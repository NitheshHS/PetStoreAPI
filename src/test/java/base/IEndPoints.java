package base;

public interface IEndPoints {

    String UPLOAD_PET_IMAGE= "/pet/{petId}/uploadImage";

    String ADD_NEW_PET = "/pet";

    String UPDATE_PET = "/pet";

    String FIND_PET_BY_STATUS = "/pet/findByStatus";

    String FIND_PET_BY_ID ="/pet/{petId}";

    String DELETE_PET = "/pet/{petId}";
}
