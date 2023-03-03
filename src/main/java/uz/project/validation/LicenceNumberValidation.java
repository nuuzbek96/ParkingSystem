package uz.project.validation;

public class LicenceNumberValidation {

        public boolean validNumber(String licenseNumber){
            if (licenseNumber.length() != 12){
                return false;
            }
            int digits = 0;
            int letters = 0;
            if (licenseNumber.charAt(2) == '|' && licenseNumber.charAt(9) == '|') {
                String[] split = licenseNumber.split("\\|");
                String a = split[0];
                String b = split[2];
                String d = split[1];
                if (Character.isDigit(a.charAt(0)) && Character.isDigit(a.charAt(1))){
                    if (b.equals("uz")){
                        boolean b1 = !Character.isDigit(d.charAt(5)) && Character.isUpperCase(d.charAt(5));
                        boolean b2 = !Character.isDigit(d.charAt(4)) && Character.isUpperCase(d.charAt(4));
                        if (Character.isDigit(d.charAt(0)) && Character.isDigit(d.charAt(1)) && Character.isDigit(d.charAt(2))){
                            if (!Character.isDigit(d.charAt(3)) && Character.isUpperCase(d.charAt(3))) {
                                if (b2) {
                                    if (b1){
                                        return true;
                                    }
                                }
                            }
                        } else if (!Character.isDigit(d.charAt(0)) && Character.isUpperCase(d.charAt(0))) {
                            if (Character.isDigit(d.charAt(1)) && Character.isDigit(d.charAt(2)) && Character.isDigit(d.charAt(3))){
                                if (b2) {
                                    if (b1){
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }else {
                    return false;
                }
            }
            for (int i = 0; i < licenseNumber.length(); i++) {
                if (Character.isDigit(licenseNumber.charAt(i))){
                    digits++;
                }
                if (Character.isLetter(licenseNumber.charAt(i)) && Character.isUpperCase(licenseNumber.charAt(i))){
                    letters++;
                }
            }
            return (digits == 6 && letters == 3);
        }
}
