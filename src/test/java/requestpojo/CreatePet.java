package requestpojo;

//{
//  "id": 0,
//  "category": {
//    "id": 0,
//    "name": "string"
//  },
//  "name": "doggie",
//  "photoUrls": [
//    "string"
//  ],
//  "tags": [
//    {
//      "id": 0,
//      "name": "string"
//    }
//  ],
//  "status": "available"
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePet {

    private Integer id;
    
    private Category category;

    private String name;

    private String[] photoUrls;

    private Tag[] tags;

    private String status;
}

