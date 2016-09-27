package au.edu.unsw.soacourse.gnafaddressingservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2016-09-27T16:29:47.093+10:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au", name = "GNAFAddressingService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface GNAFAddressingService {

    @WebResult(name = "addressExistsInFileResponse", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://GNAFAddressingService.soacourse.unsw.edu.au/addressExistsInFile")
    public AddressExistsInFileResponse addressExistsInFile(
        @WebParam(partName = "parameters", name = "addressExistsInFileRequest", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au")
        AddressExistsInFileRequest parameters
    ) throws AddressExistsFaultMsg;

    @WebResult(name = "similarAddressesInFileResponse", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://GNAFAddressingService.soacourse.unsw.edu.au/similarAddressesInFile")
    public SimilarAddressesInFileResponse similarAddressesInFile(
        @WebParam(partName = "parameters", name = "similarAddressesInFileRequest", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au")
        SimilarAddressesInFileRequest parameters
    ) throws SimilarAddressesFaultMsg;

    @WebResult(name = "returnPostcodeInfoResponse", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au", partName = "parameters")
    @WebMethod(action = "http://GNAFAddressingService.soacourse.unsw.edu.au/returnPostcodeInfo")
    public ReturnPostcodeInfoResponse returnPostcodeInfo(
        @WebParam(partName = "parameters", name = "returnPostcodeInfoRequest", targetNamespace = "http://GNAFAddressingService.soacourse.unsw.edu.au")
        ReturnPostcodeInfoRequest parameters
    ) throws ReturnPostcodeFaultMsg;
}
