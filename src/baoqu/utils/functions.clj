(ns baoqu.utils.functions)

(defn try-execute
  [fn]
  (try
    (fn)
    (catch Exception e
      (println (str "warning: " (.getMessage e))))))

(def not-nil? (complement nil?))
