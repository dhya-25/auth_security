package tn.arabsoft.auth.controler;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.arabsoft.auth.entity.Role;
import tn.arabsoft.auth.entity.Personnel;
import tn.arabsoft.auth.payload.request.LoginRequest;
import tn.arabsoft.auth.payload.request.SignupRequest;
import tn.arabsoft.auth.repository.RoleRepository;
import tn.arabsoft.auth.repository.UserRepository;
import tn.arabsoft.auth.response.JwtResponse;
import tn.arabsoft.auth.response.MessageResponse;
import tn.arabsoft.auth.security.jwt.JwtUtils;
import tn.arabsoft.auth.security.service.UserDetailsImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class Auth {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @GetMapping("/get/{mat}")
  public  Optional<Personnel> get(@PathVariable String mat){
  	return this.userRepository.findByMatriculeP(mat);
  }
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getMatriculeP(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         "Bearer", 
                         userDetails.getNomResponsable(), 
                         userDetails.getMatriculeP(),
                         userDetails.getEmail(), 
                          userDetails.getPassword(),userDetails.getNom(),userDetails.getPrenom(),userDetails.getNumTel(),userDetails.getPoste(),userDetails.getDepartement(),roles));
  }
 
 @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		/*
		 * if (userRepository.existsByMatricule(signUpRequest.getMatricule())) { return
		 * ResponseEntity .badRequest() .body(new
		 * MessageResponse("Error: Username is already taken!")); }
		 */

	/*
	 * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return
	 * ResponseEntity .badRequest() .body(new
	 * MessageResponse("Error: Email is already in use!")); }
	 */
    //Create new user's account
    Personnel user = new Personnel( signUpRequest.getEmail(), signUpRequest.getMatriculeP(), signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getDepartement(), signUpRequest.getNomResponsable(),
    		signUpRequest.getNumTel(), signUpRequest.getPoste(), signUpRequest.getPassword());

    Integer intRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findById(intRoles)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
       roles.add(userRole);
		/*
//		 * if (strRoles == null) { Role userRole =
//		 * roleRepository.findByName(ERole.ROLE_USER) .orElseThrow(() -> new
//		 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } else {
//		 * strRoles.forEach(role -> { switch (role) { case "admin": Role adminRole =
//		 * roleRepository.findByName(ERole.ROLE_ADMIN) .orElseThrow(() -> new
//		 * RuntimeException("Error: Role is not found.")); roles.add(adminRole);
//		 * 
//		 * break; case "mod": Role modRole =
//		 * roleRepository.findByName(ERole.ROLE_MODERATOR) .orElseThrow(() -> new
//		 * RuntimeException("Error: Role is not found.")); roles.add(modRole); break;
//		 * case "rh": Role rhRole = roleRepository.findByName(ERole.ROLE_RH)
//		 * .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//		 * roles.add(rhRole); break; default: Role userRole =
//		 * roleRepository.findByName(ERole.ROLE_USER) .orElseThrow(() -> new
//		 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } }); }
//		 */
//
    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    

  }

}
