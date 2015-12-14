(ns baoqu.utils.mime
  (:require [cheshire.core :as json]))

;;            _                  _
;;           (_)                | |
;;  _ __ ___  _ _ __ ___   ___  | |_ _   _ _ __   ___  ___
;; | '_ ` _ \| | '_ ` _ \ / _ \ | __| | | | '_ \ / _ \/ __|
;; | | | | | | | | | | | |  __/ | |_| |_| | |_) |  __/\__ \
;; |_| |_| |_|_|_| |_| |_|\___|  \__|\__, | .__/ \___||___/
;;                                    __/ | |
;;                                   |___/|_|

(def application-json "application/json")

;;                                    _
;;                                   (_)
;;   ___ ___  _ ____   _____ _ __ ___ _  ___  _ __  ___
;;  / __/ _ \| '_ \ \ / / _ \ '__/ __| |/ _ \| '_ \/ __|
;; | (_| (_) | | | \ V /  __/ |  \__ \ | (_) | | | \__ \
;;  \___\___/|_| |_|\_/ \___|_|  |___/_|\___/|_| |_|___/
;;


(defn to-json
  "Uses cheshire library to create a json from a data structure"
  [data]
  (json/generate-string data))
