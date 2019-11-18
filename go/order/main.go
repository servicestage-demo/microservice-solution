package main

import (
	"github.com/go-chassis/go-chassis"
	"github.com/go-chassis/go-chassis/client/rest"
	"github.com/go-chassis/go-chassis/core"
	"github.com/go-chassis/go-chassis/pkg/util/httputil"
	"github.com/go-chassis/go-chassis/server/restful"
	"github.com/go-mesh/openlogging"
	_ "github.com/huaweicse/auth/adaptor/gochassis" //must import before other imports
	"golang.org/x/net/context"
	"net/http"

	_ "github.com/go-chassis/go-chassis-cloud/provider/huawei/engine"
)

type OrderResource struct {
}

func (r *OrderResource) Get(ctx *restful.Context) {
	req, err := rest.NewRequest("GET", "http://payment/v1/payment", nil)
	if err != nil {
		openlogging.Error("new request failed.")
		return
	}

	resp, err := core.NewRestInvoker().ContextDo(context.Background(), req)
	if err != nil {
		openlogging.Error("do request failed:" + err.Error())
		return
	}
	defer resp.Body.Close()
	ctx.Write([]byte("order info " + string(httputil.ReadBody(resp))))
}

func (r *OrderResource) URLPatterns() []restful.Route {
	return []restful.Route{
		{
			Method:       http.MethodGet,
			Path:         "/v1/order",
			ResourceFunc: r.Get,
		},
	}
}
func main() {
	chassis.RegisterSchema("rest", &OrderResource{})
	err := chassis.Init()
	if err != nil {
		panic(err)
	}
	err = chassis.Run()
	if err != nil {
		panic(err)
	}
}
