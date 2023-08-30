package com.blogapi.service.Impl;

import com.blogapi.entity.Post;
import com.blogapi.exceptions.ResourceNotFoundException;
import com.blogapi.payload.CommentDto;
import com.blogapi.payload.PostDto;
import com.blogapi.repository.PostRepository;
import com.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl  implements PostService {


        private PostRepository postRepo; // recent version of SpringBoot, Instead of using @Autowired  for dependency injection, we can use  constructor based dependency injection.


    private ModelMapper modelMapper;// library we are trying to create and object based on dependency injection object can not be created, because of
    // @Autowired or constructor based dependency injection will not work directly  when it is an external library but postRepo is build in feature . that y we search on Google and inject  into porm.xml.

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) { //click generate -> constructor -> ok.
        this.postRepo = postRepo;  // this automatically be injected  postRepo object address. then @Autowired is not require.
        this.modelMapper=modelMapper; //  Constructor based dependency injection
    }

    @Override
    public PostDto createPost(PostDto postDto) {  // insert data into the database.

          Post post = mapToEntity(postDto); //// here we convert Dto to entity. here

//        Post post = new Post(); // we content to here and can go to database
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());  // dto se get and then set by Entity.
//        post.setContent(postDto.getContent());

        Post savePost = postRepo.save(post); // after save post , it will give a new object with the content telling what did I save the data in database.// Post savePost this has the  content that gone to database.

        PostDto dto = mapToDto(savePost);//return  dto back here mapToDto(savePost); // map dto convert to entity object
        // PostDto dto= new PostDto(); // convert into dto.
//        dto.setId(dto.getId()); // copy the data to Dto.
//        dto.setTitle(dto.getTitle());
//        dto.setDescription(dto.getDescription());
//        dto.setContent(dto.getContent());


        return dto;
    }

    @Override
    public PostDto getPostById(long id) { //based on the id , it should get the object  convert that it into dto and return back.
       // Optional<Post> byId = postRepo.findById(id);
        // Post post= postRepo.findById(id).get(); // when we add get() this will autometically convert to Entity // we are doing one line , it will convert optional object to  post objetc.
        Post post= postRepo.findById(id).orElseThrow(   //whenever we want throw custom exception just after findById(id).orElseThrow. // if recots is found ,you will get the object , record is not found then ()-> throw excteption by  creating the object .

                ()-> new ResourceNotFoundException(id)
        );

        PostDto dto =mapToDto(post); // give entity object to dto and that will convert entity to dto.

//        PostDto dto= new PostDto();
//        dto.setId(post.getId()); // it will convert post to dto.
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        return dto;
    }

    @Override  // notebook
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

    Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending(); // ternary operator , if true then asc if false then desc.

        Pageable  pageable = PageRequest.of(pageNo,pageSize,sort); //sort by can be String , require to convert this object String to sort object.// method of class , this is static. that's y we use class name  .
        //System.out.println(pageable+"74");  // PageRequest is inbuilt class of static method.


        Page<Post> posts  = postRepo.findAll(pageable);// findAll have alot of overloded method .// size is three that y it will generate only 3 record. // it page object convert to List objet.

        //System.out.println(posts+"79");
        // it will page of post //

        List<Post> content = posts.getContent();//getContent() does all the page object convert into list

       // System.out.println(content+"84");
        List<PostDto> postDtos =content.stream().map(post->mapToDto(post)).collect(Collectors.toList());  // to convert post object into postdto.
        return postDtos;

    }


    public  void deletePost(long id){
        Post post = postRepo.findById(id).orElseThrow(  // post not found then throw exception if found then delete the record.
                () -> new ResourceNotFoundException(id)

        );

        postRepo.deleteById(id);

    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(  // postdto to entity // old data
                () -> new ResourceNotFoundException(id)

        );
        Post updateContent = mapToEntity(postDto); // old data // new data// dto to Entity // it will return but without id number.that y we set the id in below.
        updateContent.setId(post.getId()) ; // old set to new id.
        Post updatePostInfo = postRepo.save(updateContent);// this is for new ID. // dto to entity
        return   mapToDto(updatePostInfo); // this is for new ID //entity to dto
    }

    Post mapToEntity(PostDto postDto){ // copy the dto object into Entity.


      Post post = modelMapper.map(postDto, Post.class);
//        Post post = new Post(); // Entity
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post; // return back entity
    }


    PostDto mapToDto(Post post){ // entity object convert into dto.
       PostDto dto= modelMapper.map(post, PostDto.class); //it takes all the content from post entity it will automatically copies  to Postdto object  and dto object address is store in dto.4 lines reduse one line.
//        PostDto dto= new PostDto(); // convert into dto.
//        dto.setId(post.getId()); // copy the data to Dto.
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        return dto; //return  dto back here mapToDto(savePost);

    }



//    Post mapToEntity(PostDto postDto){ // copy the dto object into Entity.
//        Post post = new Post(); // Entity
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//
//        return post; // return back entity
//    }



}
// here Two way dependency injection can done- 1. @Autowired  2.Constructor based dependency injection.

//    List<Post> post =postRepo.findAll();
//
//    List<PostDto> postDtos =posts.stream().map(post->mapToDto(post)).collect(collectors.toList());                                 // to convert post object into postdto.
//        return postDtos;