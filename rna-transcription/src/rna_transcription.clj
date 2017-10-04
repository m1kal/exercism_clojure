(ns rna-transcription
  (:require [clojure.string :as str])
)

(defn match-nucleotide [nucleotide]
  (cond
    (= nucleotide "G") "C"
    (= nucleotide "C") "G"
    (= nucleotide "A") "U"
    (= nucleotide "T") "A"
    :else (throw (AssertionError. "Wrong nucleotide"))
    )
)

(defn to-rna [code]
  (str/join "" (map match-nucleotide (str/split code #"")))
)
