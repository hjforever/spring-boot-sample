package io.hjforever.springpb;


import com.googlecode.protobuf.format.JsonFormat;
import io.hjforever.springpb.user.UserRequest;
import io.hjforever.springpb.user.UserResp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * The media-type for protobuf {@code application/x-protobuf}.
     */
    public static final MediaType PROTOBUF = new MediaType("application", "x-protobuf", DEFAULT_CHARSET);

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void user() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(PROTOBUF))
                .andReturn();
        byte[] resp = result.getResponse().getContentAsByteArray();
        UserResp userResp = UserResp.parseFrom(resp);
        System.out.println(JsonFormat.printToString(userResp));
        //System.out.println(userResp);
    }


    @Test
    public void query() throws Exception {

        UserRequest userRequest = UserRequest.newBuilder().setUserId(18).setName("谭恒杰").build();

        MvcResult result = mockMvc.perform(post("/user/query").content(userRequest.toByteArray()).contentType(PROTOBUF))
                .andExpect(status().isOk())
                .andExpect(content().contentType(PROTOBUF))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();
        byte[] resp = result.getResponse().getContentAsByteArray();
        UserResp userResp = UserResp.parseFrom(resp);

        System.out.println(JsonFormat.printToString(userResp));
        //System.out.println(userResp.toString());
    }
}
