(ns nucleotide-count)

(defn count [type strand]
  (assert (some #(= type %) '(\A \C \G \T)))
  (clojure.core/count (re-seq (re-pattern (str type)) strand) )
)

(defn nucleotide-counts [strand]
 (apply array-map (flatten (map #(vector % (count % strand)) '(\A \C \G \T))))
)
