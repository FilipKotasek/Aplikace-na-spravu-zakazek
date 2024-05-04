package Kotasek.Contract.controllers;

import Kotasek.Contract.data.entities.ContractEntity;
import Kotasek.Contract.data.repositories.ContractRepository;
import Kotasek.Contract.models.dto.ContractDTO;
import Kotasek.Contract.models.dto.mappers.ContractMapper;
import Kotasek.Contract.models.exceptions.ContractNotFoundException;
import Kotasek.Contract.models.services.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContractsController {

    @Autowired
    ContractService contractService;

    @Autowired
    ContractMapper contractMapper;

    @Autowired
    ContractRepository contractRepository;


    @ExceptionHandler(ContractNotFoundException.class)
    public String handleContractNotFoundException ( RedirectAttributes redirectAttributes) {

     redirectAttributes.addFlashAttribute("error","Zakázka nenalezena." )  ;
     return "redirect:/contracts";
    }


@GetMapping("/")
public String renderHomePage() {



return "pages/index";
}

@Secured({"ROLE_USER", "ROLE_ADMIN"})
@GetMapping("contracts")
    public String renderContracts(Model model) {

    List<ContractDTO> contracts=contractService.GetAll();
    model.addAttribute("contracts",contracts);


 return "pages/contracts";
}

@Secured("ROLE_ADMIN")
@GetMapping("create")
    public String renderCreatePage(@ModelAttribute ContractDTO contract) {

return "pages/create";
}

@GetMapping("contracts/{id}")
public String renderDetail(@PathVariable long id, Model model) {
    ContractDTO contract=contractService.GetId(id);
model.addAttribute("contract",contract);

    return "pages/detail";
}

@Secured("ROLE_ADMIN")
@PostMapping("create")
public String createContract(@Valid @ModelAttribute ContractDTO contract, BindingResult result, RedirectAttributes redirectAttributes)

{
   if (result.hasErrors()) {
      return renderCreatePage(contract);
   }

    contractService.newContract(contract);
   redirectAttributes.addFlashAttribute("success","Zakázka vytvořena");

    return "redirect:/contracts";

}

@Secured("ROLE_ADMIN")
@GetMapping("edit/{id}")
    public String renderEdit( @PathVariable long id, ContractDTO contractDTO ) {
       ContractDTO contract=contractService.GetId(id);
       contractMapper.updateContractDTO(contract,contractDTO);



    return "pages/edit";
}

@Secured("ROLE_ADMIN")
@PostMapping("edit/{id}")
    public String updateContract(@Valid @ModelAttribute ContractDTO contractDTO, BindingResult result, RedirectAttributes redirectAttributes){

    if (result.hasErrors()) {
        return renderEdit(contractDTO.getId(),contractDTO);
    }

    ContractEntity updatedContract= contractMapper.toEntity(contractDTO);

   contractService.updateContract(updatedContract);
    redirectAttributes.addFlashAttribute("success","Zakázka úspěšně editována.");
    return "redirect:/contracts";
}

@Secured("ROLE_ADMIN")
@GetMapping("delete/{id}")
    public String deleteContract(@PathVariable Long id, RedirectAttributes redirectAttributes) {

    contractService.remove(id);
    redirectAttributes.addFlashAttribute("success","Zakázka úspěšně odstraněna.");

    return "redirect:/contracts";
}

}
