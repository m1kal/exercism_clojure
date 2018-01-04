(ns two-fer)

(defn two-fer
  ([] (two-fer "you"))
  ([name] (two-fer name "me"))
  ([name me]
    (str
      (clojure.string/replace
        (clojure.string/join ", " (map #(str "one for " %) [name me]))
        #"^." #(clojure.string/upper-case %1)) "."))
)
