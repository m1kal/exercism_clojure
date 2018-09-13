(ns protein-translation)

(def codon-protein-table
  {["AUG"] "Methionine"
   ["UUU" "UUC"] "Phenylalanine"
   ["UUA" "UUG"] "Leucine"
   ["UCU" "UCC" "UCA" "UCG"] "Serine"
   ["UAU" "UAC"] "Tyrosine"
   ["UGU" "UGC"] "Cysteine"
   ["UGG"] "Tryptophan"
   ["UAA" "UAG" "UGA"] "STOP"})

(defn translate-codon [codon]
  (codon-protein-table
    (first (filter #(some #{codon} %) (keys codon-protein-table)))))

(defn translate-rna [rna]
  (->>
    rna
    (re-seq #"...")
    (map translate-codon)
    (take-while #(not (= % "STOP")))))
