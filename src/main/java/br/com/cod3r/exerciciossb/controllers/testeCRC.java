package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping()
public class testeCRC {
        private static final int CRC_POLY = 0xA001; // polinômio utilizado para o cálculo

        @GetMapping("/CRC")

        public String[] Retorno(@RequestParam(name = "id") int id,
                                @RequestParam(name = "cmd") int cmd,
                                @RequestParam(name = "msg") String msg){

            int id_lsb = id % 256;
            int id_msb = id / 256 ;
            int[] array = new int[3 + (cmd == 40?msg.length():2) + 2];
            array[0] = id_lsb;
            array[1] = id_msb;
            array[2] = cmd;
            if (cmd == 40) {
                for (int i = 0; i < msg.length(); i++) {
                    array[3 + i] = msg.charAt(i);
                }
            }else{
                array[3] = 0x00;
                array[4] = 0x00;
            }

            int crc = CalculaCRC(array, array.length);
            int crc_lsb = (crc % 256);
            int crcTesta = crc / 256;
            int crc_msb = (crcTesta & 0xFF);
            if (cmd == 40){
                array[msg.length() + 3] = crc_lsb;
                array[msg.length() + 4] = crc_msb;
            }else {
                array[5] = crc_lsb;
                array[6] = crc_msb;
            }

            System.out.println(Arrays.toString(array));
            String[] arrayStr = new String[array.length];
            for (int i = 0; i < array.length; i++) {
                arrayStr[i] = Integer.toHexString(array[i]);
            }
            return arrayStr;
        }

    public static int CalculaCRC(int [] b, int tam)
    {
        int bitbang,i,j;
        int CRC_calc;
        CRC_calc = 0xC181;
        for(i=0;i<tam;i++)
        {
            CRC_calc ^= ((int) (b[i])) & 0x00FF;
            for(j=0;j<8;j++)
            {
                bitbang = CRC_calc;
                CRC_calc >>= 1;
                int c = bitbang & 0x01;
                if((c& 0x00FF) ==1)
                {
                    CRC_calc ^= CRC_POLY;
                }
            }
        }
        return CRC_calc;
    }

}


