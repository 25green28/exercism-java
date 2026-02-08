package protein.translation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProteinTranslator {

    private static final Map<String, String> CODONS_TO_AMINO_ACIDS;

    static {
        CODONS_TO_AMINO_ACIDS = new HashMap<>();
        putInsideMap("Methionine", "AUG");
        putInsideMap("Phenylalanine", "UUU", "UUC");
        putInsideMap("Leucine", "UUA", "UUG");
        putInsideMap("Serine", "UCU", "UCC", "UCA", "UCG");
        putInsideMap("Tyrosine", "UAU", "UAC");
        putInsideMap("Cysteine", "UGU", "UGC");
        putInsideMap("Tryptophan", "UGG");
        putInsideMap("STOP", "UAA", "UAG", "UGA");

    }

    private static void putInsideMap(String aminoAcid, String ... codons) {
        for (String codon : codons) {
            CODONS_TO_AMINO_ACIDS.put(codon, aminoAcid);
        }
    }

    List<String> translate(String rnaSequence) {
        List<String> sequenceOfAminoAcids = new ArrayList<>();

        for (int i = 0; i < rnaSequence.length(); i += 3) {
            if (i + 3 >  rnaSequence.length()) {
                throw new IllegalArgumentException("Invalid codon");
            }
            String sequence = rnaSequence.substring(i, i + 3);
            String result = CODONS_TO_AMINO_ACIDS.get(sequence);
            if (result == null) {
                throw new IllegalArgumentException("Invalid codon");
            }
            if ("STOP".equals(result)) {
                break;
            }
            sequenceOfAminoAcids.add(result);
        }

        return sequenceOfAminoAcids;
    }
}
