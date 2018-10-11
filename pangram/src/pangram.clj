(ns pangram)

(defn pangram? [input]
  (every?
    #(re-find (re-pattern (str (char %))) (clojure.string/lower-case input))
    (range (int \a) (inc (int \z)))))
