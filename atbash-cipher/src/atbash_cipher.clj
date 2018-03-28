(ns atbash-cipher
  (:require [clojure.string :refer [join lower-case replace trim]]))

(def substitutions
  (apply zipmap
    (map
      #(% (map char (range (int \a) (inc (int \z)))))
      [identity reverse])))

(defn encode-char [input]
  (or (substitutions input) input))

(defn split-text [input]
  (replace input #"(.....)" "$1 "))

(defn encode [plaintext]
  (->>
    (->
      plaintext
      lower-case
      (replace #"[^a-z0-9]" "")
    )
    (map encode-char)
    (join "")
    split-text
    trim))
