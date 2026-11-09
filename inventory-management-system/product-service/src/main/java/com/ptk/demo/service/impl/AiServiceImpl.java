//
//package com.ptk.demo.service.impl;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Service;
//
//import com.ptk.demo.service.AiService;
//
//@Service
//public class AiServiceImpl implements AiService {
//
//    private final ChatClient chatClient;
//
//    public AiServiceImpl(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }
//
//    @Override
//    public String generateMyanmarCategoryName(String categoryName) {
//
//        return chatClient.prompt()
//                .user("""
//                        Translate the following English category name directly into Myanmar language.
//
//                        English category name: %s
//
//                        Requirements:
//                        - Translate the category name only.
//                        - Keep exactly the same meaning.
//                        - Do not generate a category description.
//                        - Do not add explanations or extra words.
//                        - Use natural and commonly understood Myanmar terminology.
//                        - Return only the Myanmar category name.
//                        """.formatted(categoryName))
//                .call()
//                .content();
//    }
//
//    @Override
//    public String generateMyanmarCategoryDescription(String categoryDescription) {
//
//        return chatClient.prompt()
//                .user("""
//                        Translate the following English category description into Myanmar language.
//
//                        Category description:
//                        %s
//
//                        Requirements:
//                        - Keep exactly the same meaning as the original description.
//                        - Do not change or invent category features.
//                        - Use natural and commonly understood Myanmar language.
//                        - Make it suitable for an e-commerce website.
//                        - Keep the description short and clear.
//                        - Return only the Myanmar description.
//                        """.formatted(categoryDescription))
//                .call()
//                .content();
//    }
//}
