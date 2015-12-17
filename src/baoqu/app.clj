(ns baoqu.app
  (:require [catacumba.core :as cat]
            [catacumba.handlers.misc :as misc]
            [catacumba.handlers.parse :as parse]
            [baoqu.routes.home :as home]
            [baoqu.routes.users :as users]
            [baoqu.routes.events :as events]
            [baoqu.init.fixtures :as fix])
  (:gen-class))

;;             _                               _
;;            (_)                             (_)
;;  _   _ _ __ _   _ __ ___   __ _ _ __  _ __  _ _ __   __ _ ___
;; | | | | '__| | | '_ ` _ \ / _` | '_ \| '_ \| | '_ \ / _` / __|
;; | |_| | |  | | | | | | | | (_| | |_) | |_) | | | | | (_| \__ \
;;  \__,_|_|  |_| |_| |_| |_|\__,_| .__/| .__/|_|_| |_|\__, |___/
;;                                | |   | |             __/ |
;;                                |_|   |_|            |___/

;; CORS
(def cors-conf {:origin "*"
                :max-age 3600
                :allow-headers ["X-Requested-With", "Content-Type"]})

;; URL MAPPINGS
(def app
  (cat/routes [
               [:prefix "api"
                ;; REST
                [:any (parse/body-params)]
                [:any (misc/autoreloader)]
                [:any (misc/cors cors-conf)]
                [:get "meta" #'home/hello-baoqu]
                [:prefix "users"
                 [:get #'users/list]
                 [:post "create" #'users/create]]
                [:prefix "events"
                 [:get #'events/list]
                 [:post "create" #'events/create]
                 [:post ":id/users" #'events/join]]]
               ;; WEB SOCKETS
               [:prefix "ws" [:any #'events/subscribe-to-events]]]))

;; MAIN ENTRY
(defn -main
  [& args]
  (cat/run-server app)
  (fix/load-all))
