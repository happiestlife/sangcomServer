package capstone.sangcom.service.auth.master;

import capstone.sangcom.dto.auth.AuthStudentDTO;
import capstone.sangcom.entity.ExcelData;
import capstone.sangcom.entity.JwtUser;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.auth.AuthStudentRepository;
import capstone.sangcom.repository.dao.AuthStudentDAO;
import capstone.sangcom.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MasterAuthServiceImpl implements MasterAuthService{

    private final String XLSX = "xlsx";

    private final String XLS = "xls";

    private final AuthStudentRepository authStudentRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean insertStudent(MultipartFile file) throws IOException {
        ArrayList<ExcelData> data = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if(!extension.equals(XLSX) && !extension.equals(XLS))
            return false;

        Workbook workbook = null;

        try {
            if (extension.equals(XLSX))
                workbook = new XSSFWorkbook(file.getInputStream());
            else
                workbook = new HSSFWorkbook(file.getInputStream());
        }catch(IOException e){
            log.info("[IOException] Error is occurred during opening file");

            return false;
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelData cell = new ExcelData(
                    row.getCell(0).getStringCellValue(),
                    (int)row.getCell(1).getNumericCellValue(),
                    (int)row.getCell(2).getNumericCellValue(),
                    (int)row.getCell(3).getNumericCellValue()
            );

            data.add(cell);
        }

        for (ExcelData datum : data) {
            String schoolGrade = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - (datum.getSchoolGrade() - 1));
            String schoolClass = datum.getSchoolClass() < 10 ? "0" + datum.getSchoolClass() : String.valueOf(datum.getSchoolClass());
            String schoolNumber = datum.getSchoolNumber() < 10 ? "0" + datum.getSchoolNumber() : String.valueOf(datum.getSchoolNumber());

            String studentId = schoolGrade + schoolClass + schoolNumber;

            AuthStudentDAO authData = new AuthStudentDAO(studentId, datum.getName());

            if(authStudentRepository.find(authData) != null)
                continue;

            if(authStudentRepository.insert(authData) == null)
                return false;
        }

        return true;
    }

    @Override
    public List<AuthStudentDAO> getStudents() {
        return authStudentRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteStudent(ArrayList<AuthStudentDTO> list) {
        for (AuthStudentDTO authStudentDTO : list) {
            if(!authStudentRepository.delete(new AuthStudentDAO(authStudentDTO.getStudentId(), authStudentDTO.getName())))
                return false;
        }
        return true;
    }

    @Override
    public List<JwtUser> getRegisteredStudent() {
        List<User> users = userRepository.findAll();
        if(users == null)
            return null;

        ArrayList<JwtUser> result = new ArrayList<>();

        for (User user : users) {
            result.add(userToJwtUser(user));
        }

        return result;
    }

    private JwtUser userToJwtUser(User user) {
        return new JwtUser(user.getId(), user.getSchoolgrade(), user.getSchoolclass(),
                user.getSchoolnumber(), user.getRole(), user.getYear(), user.getName());
    }
}
